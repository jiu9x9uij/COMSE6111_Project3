package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.AssociationRule;
import models.ItemSet;

import com.csvreader.CsvReader;

public class DataMiner {
	private CsvReader reader;
	private double minSup, minConf;
	private List<String> items;
	private List<List<Integer>> transactions;
	private List<ItemSet> L;
	private List<AssociationRule> rules;
	
	
	/**
	 * Initialize data, compute frequent itemsets and high confidence association rules.
	 * @param data_file_path Path of CSV file.
	 * @param minSup
	 * @param minConf
	 */
	public DataMiner(String data_file_path, double minSup, double minConf) {		
		try {
			this.reader = new CsvReader(data_file_path);
			this.minSup = minSup;
			this.minConf = minConf;
			this.items = new ArrayList<String>();
			this.transactions = new ArrayList<List<Integer>>();
			this.L = new ArrayList<ItemSet>();
			this.rules = new ArrayList<AssociationRule>();
			
			initializeData();
			frequentItemsets();
			highConfidenceAssociationRules();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Failed to locate file. Please make sure file path is correct.");
		}
	}

	/**
	 *  From input CSV file,
	 *  build transaction database,
	 *  build item database (i.e. give each unique item an ID),
	 *  build L_1 (large 1-itemsets) and store pruned result in L.
	 **/
	private void initializeData() {
		System.out.println("######### initializeData #########");///
		List<ItemSet> L_1 = new ArrayList<ItemSet>();;
		
		try {
			while (reader.readRecord()) {
				List<Integer> transaction = new ArrayList<Integer>();
	            
				for (int colIdx = 0; colIdx < reader.getColumnCount(); colIdx++) {
					String cell = reader.get(colIdx);
					int itemID = items.indexOf(cell);
					ArrayList<Integer> levelOneItems = new ArrayList<Integer>();
					ItemSet levelOneItemSet;
					int levelOneItemSetIdx;
					
					// Assign itemID to new items
					if (itemID == -1) {
						items.add(cell);
						itemID = items.indexOf(cell);
					}
					
					// Add to current transaction
					transaction.add(itemID);
					
					// Update counts in L_kp
					levelOneItems.add(itemID);
					levelOneItemSet = new ItemSet(levelOneItems, 1);
					levelOneItemSetIdx = L_1.indexOf(levelOneItemSet);
					if (levelOneItemSetIdx == -1) {
						L_1.add(levelOneItemSet);
					} else {
						L_1.get(levelOneItemSetIdx).increaseSupportCount();
					}
					
//					System.out.println(L_1.toString());///
//					System.out.println("itemID = " + itemID);///
//					System.out.println(cell);///
				}
				transactions.add(transaction);
//				System.out.println("items.size() = " + items.size());///
//				System.out.println(items.toString());///
//				System.out.println(transaction.toString());///
//				System.out.println();///
			}
			
			reader.close();
			System.out.println("items.size() = " + items.size());///
			System.out.println("transactions.size() = " + transactions.size());///
			System.out.println("L_1.size() = " + L_1.size());///
			System.out.println("L_1 = " + L_1.toString());///
			
			// Prune L_1 with minSup
			for (ItemSet is: L_1) {
				if (is.getSupportCount()/(double)transactions.size() >= minSup) {
					L.add(is);
				}
			}
			
			System.out.println("L = " + L.toString());///
		} catch (IOException e) {
			System.out.println("ERROR: Failed to read CSV file. Please make sure the file is supported.");
		} 
		
		System.out.println("############# end ############");///
	}
	
	/**
	 * Compute frequent itemsets using standard Apriori algorithm.
	 */
	private void frequentItemsets() {
		System.out.println("######### apriori #########");///
		List<ItemSet> L_kp = new ArrayList<ItemSet>(); // L_(k-1)
		L_kp.addAll(L);
		List<ItemSet> L_k;
		
		int q = 0;
		while (L_kp.size() != 0) {	
			System.out.println("L_kp = " + L_kp.toString());///
			
			L_k = new ArrayList<ItemSet>();
			
			List<ItemSet> C_k = aprioriGen(L_kp);
			
			for (List<Integer> t: transactions) {
//				System.out.println("t = " + t.toString());///
				// Find subset C_t of C_k that is contained in transaction t
				for (ItemSet is: C_k) {
//					System.out.println("\tis = " + is.toString());///
					boolean inC_t = true;
					for (Integer i: is.getItems()) {						
//						System.out.println("\t\ti = " + i + " t.indexOf(i) = " + t.indexOf(i));///
						if (t.indexOf(i) == -1) {
							inC_t = false;
							break;
						}
					}
					
					// Increase support counts of item sets in C_k that belongs to C_t
					if (inC_t) {
						is.increaseSupportCount();
//						System.out.println("\tincr -> C_k = " + C_k.toString());///
						
					}
				}
			}
			
			// Prune C_k with minSup to generate Lk, and add to LFinal
			for (ItemSet is: C_k) {
				if (is.getSupportCount()/(double)transactions.size() >= minSup) {
					L_k.add(is);
				}
			}
			System.out.println("L_k = " + L_k.toString() + "\n");///
			L.addAll(L_k);
			L_kp = L_k;
		}
		
		System.out.println("L = " + L.toString());///
		System.out.println("############# end ############");///
	}
	
	/**
	 * Generate k-itemsets according to previously pruned (k-1)-itemsets.
	 * @param L_kp Set of large (k-1)-itemsets.
	 * @return Set of all possible k-itemsets.
	 */
	private List<ItemSet> aprioriGen(List<ItemSet> L_kp) {
		System.out.println("\t========= aprioriGen =========");///
		List<ItemSet> C_k = new ArrayList<ItemSet>();
		
		/* Join step */
		for (ItemSet is: L_kp) {
			for (int index = L_kp.indexOf(is) + 1; index < L_kp.size(); index++) {
				List<Integer> itemSet1 = is.getItems();
				List<Integer> itemSet2 = L_kp.get(index).getItems();
				if (sameExceptLast(itemSet1, itemSet2)) {
					List<Integer> expandedItemSet = new ArrayList<Integer>();
					for (Integer i: itemSet1) {
						expandedItemSet.add(i);
					}
					expandedItemSet.add(itemSet2.get(itemSet2.size()-1));
					C_k.add(new ItemSet(expandedItemSet, 0));
				}
			}
		}
		
		/* TEST pruning with example in paper 
		L_kp = new ArrayList<ItemSet>();
		L_kp.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 2, 3)), 1));
		L_kp.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 2, 4)), 1));
		L_kp.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 3, 4)), 1));
		L_kp.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 3, 5)), 1));
		L_kp.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(2, 3, 4)), 1));
		
		C_k = new ArrayList<ItemSet>();
		C_k.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)), 1));
		C_k.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 3, 4, 5)), 1));
		*/
		
		/* Prune step */
		List<ItemSet> PrunedC_k = new ArrayList<ItemSet>();
//		System.out.println("\tL_kp = " + L_kp.toString());///
		System.out.println("\tC_k = " + C_k.toString());///
		for (ItemSet is: C_k) {
			List<List<Integer>> subsets = generateKpSubsets(is.getItems());
			System.out.println("\t\tis = " + is.toString());///
			System.out.println("\t\tsubsets = " + subsets.toString());///
			boolean allSubsetsInLkp = true;
			for (List<Integer> s: subsets) {
				// If any of the (k-1)-subset of current item set is not in L_kp,
				// current item set is pruned from C_k
//				System.out.println("\t\t\ts = " + s.toString() + " idx = " + L_kp.indexOf(s));///
				if (L_kp.indexOf(new ItemSet(s, -1)) == -1) {
					allSubsetsInLkp = false;
					break;
				}
			}
			if (allSubsetsInLkp) {
				PrunedC_k.add(is);
//				System.out.println("\t\tadd -> PrunedC_k = " + C_kFinal.toString());///
			}
		}
		System.out.println("\tPrunedC_k = " + PrunedC_k.toString());///
		
		System.out.println("\t============= end ============");///
		return PrunedC_k;
	}
	
	/**
	 * Given two lists of integers, compare to the second last integer.
	 * @param itemSet1
	 * @param itemSet2
	 * @return <tt>true</tt> if they only differ by the last integer; <tt>false</tt> otherwise.
	 */
	private boolean sameExceptLast(List<Integer> itemSet1, List<Integer> itemSet2) {
		boolean result = true;
		for (int i = 0; i < itemSet1.size() - 1; i++) {
			if (!itemSet1.get(i).equals((itemSet2).get(i))) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Given a list of integers of length n, generate all its subsets of length (n-1).
	 * @param list
	 * @return A list of (n-1)-length subsets.
	 */
	private List<List<Integer>> generateKpSubsets(List<Integer> list) {
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < list.size(); i++) {
			List<Integer> subset = new ArrayList<Integer>();
			for (int j = 0; j < list.size(); j++) {
				if (j != list.size()-1-i) {
					subset.add(list.get(j));
				}
			}
			if (!subset.isEmpty()) subsets.add(subset);
		}
		
		return subsets;
	}
	
	/**
	 * Given that frequent itemsets are computed, compute high confidence association rules.
	 */
	private void highConfidenceAssociationRules() {
		System.out.println("###### associationRules ######");///
		
		/* TEST subsets  
		L = new ArrayList<ItemSet>();
		L.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4)), 1));
		L.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 2, 4)), 1));
		L.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 3, 4)), 1));
		L.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(1, 3, 5)), 1));
		L.add(new ItemSet(new ArrayList<Integer>(Arrays.asList(2, 3, 4)), 1));
		*/
		
		for (ItemSet is: L) {
			if (is.getItems().size() > 1) {
				List<List<Integer>> subsets = generateSubsets(is.getItems());
				System.out.println("subsets = " + subsets);///
				for (List<Integer> x: subsets) {
					List<Integer> y = new ArrayList<Integer>();
					for (Integer it: is.getItems()){
						// Y = ItemSet - X
						if (x.indexOf(it) == -1) y.add(it);
					}
					double support = is.getSupportCount()/(double)transactions.size();
					double supportOfX = (L.get(L.indexOf(new ItemSet(x, -1))).getSupportCount()/(double)transactions.size());
					double confidence = support/supportOfX;
					
					// Add rule only when confidence is high enough
					if (confidence >= minConf) {
						rules.add(new AssociationRule(x, y, support, confidence));
					}
				}
			}
		}
		System.out.println("############# end ############");///
	}
	
	/**
	 * Given a list of integers, generate all its non-empty subsets.
	 * @param startList
	 * @return A list of non-empty subsets.
	 */
	private List<List<Integer>> generateSubsets(List<Integer> startList) {
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();
		
		generateKpSubsets(subsets, startList);
		
		return subsets;
	}
	
	/**
	 * Helper method for generateSubsets(List).
	 * @param subsets The final list of non-empty subsets.
	 * @param list Subset of length n that is currently being processed to generate its subsets of length (n-1).
	 */
	private void generateKpSubsets(List<List<Integer>> subsets, List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			List<Integer> subset = new ArrayList<Integer>();
			for (int j = 0; j < list.size(); j++) {
				if (j != list.size()-1-i) {
					subset.add(list.get(j));
				}
			}
			if (!subset.isEmpty()) {
				subsets.add(subset);
				generateKpSubsets(subsets, subset);
			}
		}
	}
	
	/**
	 * Print in specified format all the frequent subsets.
	 */
	private void printFrequentItemSets() {		
		System.out.println("==Frequent itemsets (min_sup=" + minSup*100 + "%)");
		for (ItemSet is: L) {
			System.out.print("[");
			List<Integer> itemIDs = is.getItems();
			for (int i = 0; i < itemIDs.size(); i++) {
				if (i == itemIDs.size()-1) {
					System.out.print(items.get(itemIDs.get(i)));
				} else {
					System.out.print(items.get(itemIDs.get(i)) + ",");
				}
			}
			System.out.println("], " + (int)(is.getSupportCount()/(double)transactions.size()*100) + "%");
		}
	}
	
	/**
	 * Print in specified format all the high frequency association rules.
	 */
	private void printHighConfidenceAssociationRules() {		
		System.out.println("==High-confidence association rules (min_conf=" + minConf*100 + "%)");
		for (AssociationRule r: rules) {
			System.out.print("[");
			List<Integer> XItemIDs = r.getX();
			for (int i = 0; i < XItemIDs.size(); i++) {
				if (i == XItemIDs.size()-1) {
					System.out.print(items.get(XItemIDs.get(i)));
				} else {
					System.out.print(items.get(XItemIDs.get(i)) + ",");
				}
			}
			System.out.print("] => [");
			List<Integer> YItemIDs = r.getX();
			for (int i = 0; i < YItemIDs.size(); i++) {
				if (i == YItemIDs.size()-1) {
					System.out.print(items.get(YItemIDs.get(i)));
				} else {
					System.out.print(items.get(YItemIDs.get(i)) + ",");
				}
			}
			System.out.print("] ");
			System.out.print("(Conf: " + (int)(r.getConfidence()*100) + "%, ");
			System.out.println("Supp: " + (int)(r.getSupport()*100) + "%)");
		}
	}

	
	/**
	 * Prompt user to specify a minimum support and a minimum confidence.
	 * Execute mining process and print result to screen.
	 * @param args
	 */
	public static void main(String[] args) {
		double minSup = -1;
		double minConf = -1;
		/* Prompt for minSup and minConf */
		Scanner scanner = new Scanner(System.in);
		String input;
		
		do {
			try {
				System.out.println("Please enter minimum support:");
				input = scanner.next();
				minSup = Double.parseDouble(input);
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Minimum support must be a double.\n");
			}
		} while (minSup < 0);
		
		do {
			try {
				System.out.println("Please enter minimum confidence:");
				input = scanner.next();
				minConf = Double.parseDouble(input);
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Minimum confidence must be a double.\n");
			}
		} while (minConf < 0);
			
		
		/* Mine and print result */
		DataMiner dataMiner = new DataMiner("INTEGRATED-DATASET.csv", minSup, minConf); 
		dataMiner.printFrequentItemSets();
		System.out.println();
		dataMiner.printHighConfidenceAssociationRules();
	}
}

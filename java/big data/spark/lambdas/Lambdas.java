package stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.ImmutablePair;

import common.CommonStrings;

public class Lambdas {

	public static void main(String[] args) {
		List<String> myList = CommonStrings.getAlphabetStrings();
		
//		printListThreeWays(myList);
//		printWordsWithLetterA(myList);
		
		
//		System.out.println("***Before Sorting***");
//		printList(myList);
//		sortLexicographically(myList);
//		System.out.println("***After Sorting***");
//		printList(myList);
		
//		System.out.println("***Before Sorting Reverse***");
//		printList(myList);
//		sortReverseLexicographically(myList);
//		System.out.println("***After Sorting Reverse***");
//		printList(myList);
		
//		System.out.println("***Before Sorting Random***");
//		sortLexicographically(myList);
//		printList(myList);
//		sortRandom(myList);
//		System.out.println("***After Sorting Random***");
//		printList(myList);
		
		List<ImmutablePair<String, Integer>> listOfImmutablePairs = createListOfImmutablePairs(myList);
		printListOfImmutablePairs(listOfImmutablePairs);
	}
	
	public static void printList(List<String> myList) {
		for (String myString : myList) {
			System.out.println(myString);
		}
	}
	
	public static void printListThreeWays(List<String> myList) {
		// Manual for loop
		System.out.println("***Manual for loop***");
		for (int i = 0; i < myList.size(); i++) {
			System.out.println(myList.get(i));
		}
		
		System.out.println("---------------------");
		
		// Java 1.5 for loop
		System.out.println("***Java 1.5 for loop***");
		for (String myString : myList) {
			System.out.println(myString);
		}
		
		System.out.println("---------------------");
		
		// Java 1.8 for each loop
		System.out.println("***Java 1.8 for each loop***");
		myList.forEach(new Consumer<String>() {
			@Override
			public void accept(String myString) {
				System.out.println(myString);
			}
		});
	}
	
	public static void printWordsWithLetterA(List<String> myList) {
		// Manual for loop
				System.out.println("***Manual for loop***");
				for (int i = 0; i < myList.size(); i++) {
					if (myList.get(i).contains("a")) {
						System.out.println(CommonStrings.getAlphabetStrings().get(i));
					}
				}
				
				System.out.println("---------------------");
				
				// Java 1.5 for loop
				System.out.println("***Java 1.5 for loop***");
				for (String myString : myList) {
					if (myString.contains("a")) {
						System.out.println(myString);
					}
				}
				
				System.out.println("---------------------");
				
				// Java 1.8 for each loop
				System.out.println("***Java 1.8 for each loop***");
				myList.forEach(new Consumer<String>() {
					@Override
					public void accept(String myString) {
						if (myString.contains("a")) {
							System.out.println(myString);
						}
					}
				});
	}
	
	public static void sortLexicographically(List<String> stringList) {
		stringList.sort((String o1, String o2) -> {
			return o1.compareTo(o2);
		});
	}
	
	public static void sortReverseLexicographically(List<String> stringList) {
		stringList.sort((String o1, String o2) -> {
			return o2.compareTo(o1);
		});
	}
	
	public static void sortRandom(List<String> stringList) {
		Collections.shuffle(stringList, new Random());
	}
	
	public static List<ImmutablePair<String, Integer>> createListOfImmutablePairs(List<String> myList) {
		List<ImmutablePair<String, Integer>> newList = new ArrayList<ImmutablePair<String, Integer>>();
		
		myList.stream()
			.map(word -> new ImmutablePair<String, Integer>(word, word.length()))
			.forEach(wordLengthPair -> newList.add(wordLengthPair));
		
		return newList;
	}
	
	public static void printListOfImmutablePairs(List<ImmutablePair<String, Integer>> myList) {
		for (ImmutablePair<String, Integer> myPair : myList) {
			System.out.println("(" + myPair.getLeft() + ", " + myPair.getRight().toString() + ")");
		}
	}
}

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
		// Lambda notation 1
		System.out.println("***Lambda notation 1***");
		myList.forEach((String myString) -> System.out.println(myString));
		
		System.out.println("---------------------");
		
		// Lambda notation 2
		System.out.println("***Lambda notation 2***");
		myList.forEach(myString -> System.out.println(myString));
		
		System.out.println("---------------------");
		
		// Lambda notation 3
		System.out.println("***Lambda notation 3***");
		myList.forEach(System.out::println);
	}
	
	public static void printWordsWithLetterA(List<String> myList) {
		myList.forEach((String myString) -> {
			if (myString.contains("a")) {
				System.out.println(myString);
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

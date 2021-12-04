package com.npkompleet.receipt;

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.npkompleet.model.Item;

public class InputParser {

    private static final String EXEMPTED_ITEMS_REGEX = "book|food|pill|chocolate|medical";
    private static final String IMPORTED_ADJECTIVE = "imported";

    public static Item parseItem(String inputLine) {
        String[] wordArray = inputLine.trim().split(" ");
        Supplier<Stream<String>> wordStreamSupplier = () -> Stream.of(wordArray);

        Item parsedItem = new Item();
        parsedItem.setQuantity(Integer.parseInt(wordArray[0]));
        parsedItem.setAmount(Float.parseFloat(wordArray[wordArray.length - 1]));
        parsedItem.setImported(wordStreamSupplier.get().anyMatch(word -> word.equals(IMPORTED_ADJECTIVE)));
        parsedItem.setBasicTaxExempt(
                wordStreamSupplier.get().anyMatch(word -> Pattern.compile(EXEMPTED_ITEMS_REGEX).matcher(word).find()));

        // To get the item name it is necessary to do the following:
        // - Remove the numbers which will get rid of the item quantity and amount
        // - Remove the word "imported" if it is in the input
        // - Remove the word "at" from the input as well
        // - Collect the remaining word into an array and join them together.
        Pattern numberPattern = Pattern.compile("\\d+(\\.\\d+)?");
        List<String> nameWordList = wordStreamSupplier.get().filter(word -> !numberPattern.matcher(word).matches())
                .filter(word -> !word.equals(IMPORTED_ADJECTIVE))
                .filter(word -> !word.equals("at"))
                .collect(Collectors.toList());
        String[] nameWordArray = new String[nameWordList.size()];
        nameWordList.toArray(nameWordArray);
        parsedItem.setName(String.join(" ", nameWordArray));

        return parsedItem;
    }
}

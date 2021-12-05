package com.npkompleet.receipt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.npkompleet.model.Cost;
import com.npkompleet.model.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiptPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptPrinter.class);

	public static void main(String[] args) {

		for (int i = 0; i < args.length; i++) {
			System.out.printf("Output %d:%n", i + 1);
			try (FileInputStream fstream = new FileInputStream(args[i]);
					BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));) {
				String line;
				ArrayList<Cost> costList = new ArrayList<>();
				while ((line = reader.readLine()) != null) {
					if (InputValidator.isValidInput(line)) {
						Item item = InputParser.parseItem(line);
						Cost cost = new Cost(item);
						String importedAdjective = item.isImported() ? "imported " : "";
						System.out.printf("%d %s%s: %.2f%n", item.getQuantity(), importedAdjective, item.getName(),
								cost.getItemCost());
						costList.add(cost);
					} else {
						System.out.println("Not a valid input file format");
						break;
					}
				}
				Supplier<Stream<Cost>> costStreamSupplier = () -> costList.stream();
				float salesTaxSum = costStreamSupplier.get().map(cost -> cost.getItemTotalSalesTax()).reduce(0f,
						(subtotal, itemSalesTax) -> subtotal + itemSalesTax);
				float totalCost = costStreamSupplier.get().map(cost -> cost.getItemCost()).reduce(0f,
						(subtotal, itemCost) -> subtotal + itemCost);

				System.out.printf("Sales Tax: %.2f%n", salesTaxSum);
				System.out.printf("Total: %.2f%n", totalCost);
				System.out.println("");
			} catch (FileNotFoundException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

}

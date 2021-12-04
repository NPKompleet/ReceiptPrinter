package com.npkompleet.receipt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiptPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptPrinter.class);

	public static void main(String[] args) {

		for (String arg : args) {
			try (FileInputStream fstream = new FileInputStream(arg);
					BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));) {
				String line;
				while ((line = reader.readLine()) != null) {
					if (InputValidator.isValidInput(line)) {
						System.out.println(line);
					} else {
						System.out.println("Not a valid input file format");
						break;
					}
				}
			} catch (FileNotFoundException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

}

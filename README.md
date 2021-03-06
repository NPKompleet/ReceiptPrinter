# ReceiptPrinter

This program calculates the sales tax on some items purchased and prints a receipt with the total cost.

## Assumptions

1. Each line of the input detail will contain at least 4 words and at most 7 words.
2. Each input line must contain the word "at". And it must be the second to last word and must be exactly lowercase.
3. The cost of each item will always appear last in the input line and it must be a POSITIVE number.
4. The quantity of the item will always appear first in the input line and it must be a POSITIVE number.
5. The names of the sales tax exempted items are: book, food, pill, medical and chocolate. Only these 5 word wil be considered in this context. Either in singular or plural form. More words can always be added later.

## Running the Application

Clone the repo and run the following commands:

```
cd <project folder>
mvn clean install
mvn exec:java -Dexec.args="input_1 input_2 input_3"
```

This should run the application with the 3 input files in the project and generate the output.

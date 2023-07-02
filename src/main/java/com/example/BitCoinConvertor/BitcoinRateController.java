package com.example.BitCoinConvertor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BitcoinRateController {

    @GetMapping("/bitcoinRateToWords")
    public BitcoinRateResponse bitcoinRateToWords(@RequestParam("rate") String rate) {
        String[] ones = {
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen"
        };
        String[] tens = {
                "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        String[] parts = rate.replace(",", "").split("\\.");
        String integerPart = parts[0];
        int number = Integer.parseInt(integerPart);

        String rateInWords = "";
        if (number == 0) {
            rateInWords = "Zero";
        } else if (number < 20) {
            rateInWords = ones[number];
        } else if (number < 100) {
            rateInWords = tens[number / 10] + " " + ones[number % 10];
        } else if (number < 1000) {
            rateInWords = ones[number / 100] + " Hundred " + convertToWords(number % 100);
        }

        BitcoinRateResponse response = new BitcoinRateResponse(rate, rateInWords);
        return response;
    }

    private String convertToWords(int number) {
        String[] ones = {
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen"
        };
        String[] tens = {
                "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        String words;
        if (number == 0) {
            words = "";
        } else if (number < 20) {
            words = ones[number];
        } else if (number < 100) {
            words = tens[number / 10] + " " + ones[number % 10];
        } else {
            words = ones[number / 100] + " Hundred " + convertToWords(number % 100);
        }

        return words;
    }

    public static class BitcoinRateResponse {
        private String rate;
        private String rate_in_words;

        public BitcoinRateResponse(String rate, String rate_in_words) {
            this.rate = rate;
            this.rate_in_words = rate_in_words;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getRate_in_words() {
            return rate_in_words;
        }

        public void setRate_in_words(String rate_in_words) {
            this.rate_in_words = rate_in_words;
        }
    }
}

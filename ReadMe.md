LanguageDetector
================

This is a repository to predict language of the given text. Currently, it can differentiate between English, German and French.

The Approach
============

It is based on naive bayes classifier. We generate n-grams of size 1, 2 and 3 characters and feed them to the classifier. The language with the maximum probability is considered as the page language.

The prior counts are taken from another github repository [https://github.com/shuyo/language-detection].
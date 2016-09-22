LanguageDetector
================

This is a repository to predict language of the given text. Currently, it can differentiate between English, German and French.

The Approach
============

It is based on naive bayes classifier. We generate n-grams of size 1, 2 and 3 characters and feed them to the classifier. The language with the maximum probability is considered as the page language.

The prior counts, (i.e. the language profile counts) are taken from another github repository [https://github.com/shuyo/language-detection].

Usage
======

```cmd
cd jar
java -jar LanguagePredictor.jar this is a line of text for which we want to analyze the language
Predicted page langauge = en
```

You can also add text to a file, say inputfile and call the program as

```cmd
cd jar
cat inputfile | xargs java -jar LanguagePredictor.jar
```

Caveat
=======

It only works for English, French and German. If you give some random language or something junk, it will give invalid answer.
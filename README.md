# Discovering of Emerging Topics in Social Streams

This is a project based on a research paper for detecting emerging/popular topics in social media platforms like youtube, facebook, twitter, etc for a particular region. The reference research paper is attached to this repository for in depth understanding of the terms used for this project. 

This project was developed with java 7, which integreates an default twitter account to the application for fetching data real time from twitter API.

# Overall Outline

Generallly, for detecting the popular topics we employ term frequency approach (i.e. number of time the word occurs in the text) to find the most discussed topics, but in social media platform that apporach doesn't work because of the presense of synonyms and homonyms for a word. So, we used the link anomoly detection approach (it uses characteristics of a social media platform like mentions, likes and hash tags) for finding the emerging topics. In this project we have compared the two approachs and obtain the results in the form of histogram chart which would depict that link anamoly detection approach performs better than the conventional term frequency approach. For the puropose of comparision we have used twitter as the social media platform with twitter account and API keys integrated to it.

# Steps to run the Project

* Copy the project to any drive.
* Open the project in eclipse or any other IDE you are comfortable with.
* Run the the project.
* First select key based detection - get twitter trends, perform training, aggregation and finally change point analysis.
* Second select link based detection - get twitter trends, perform training, aggregation and finally change point analysis.
* Finally select burst analysis to retrieve the comparison in the form of histogram chart between two approaches.

# Note

If the project doesn't execute, use earlier version of jdk and jvm. The other reason the project will not execute if the API keys have expired. 

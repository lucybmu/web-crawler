# web-crawler

How to build:

mvn clean install

How to run it:

Go to the target folder, using the following command:

	java -jar web-crawler-1.0.0-SNAPSHOT-jar-with-dependencies.jar the-website-to-check maximun-page-to-visit
	
For example:

	java -jar web-crawler-1.0.0-SNAPSHOT-jar-with-dependencies.jar http://www.tesco.com 1000

The above example will check the www.tesco.com website, it will stop after visiting 1000 pages on the tesco.com domain. It will download all the links from the 1000 pages visited. It will store the links in two types:

	1. the links to web pages in the same domain
	
	2. the links to outside domain and the links to the static source in the same domain, such as tags like img, script, etc. 

If the maximum-page-to-visit is not provided, it will visit the whole site. If both the-website-to-check and maximun-page-to-visit are not provided, it will use http://www.hipp.co.uk as default site.

The output will be printed into a file named as site_map.txt in the target folder.





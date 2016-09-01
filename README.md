# John Given Wines, Inc. Wine Portfolio v1.0
### For Android
A portfolio app for John Given Wines, Inc. displaying all their wines.

##Initial Activity:  
When app starts, checks with server for version information. If verions are different, app deletes all local JSONS and downloads them from the server. When done, shows the "View Portfolio" button.  

##Main Activity:
Uses 4 fragments to shift UI content by way of ViewPager.  
   1. News Fragment:  
  -Tastings loaded from JSON file.  
  -Tastings parse address from JSON, and create link. Link sends Intent to maps to find location of tasting.  
  -New Vintages marked in JSON file and loaded.  
   2.  Regions Fragment:  
  -Regions loaded via RecyclerView.  
  -Wines in regions are RecyclerViews nested in Regions.  
   3. All Wines Fragment:  
  -All wines loaded into RecyclerView.  
   4. About JGWines:  
  -Contact info: Email lets user choose email app to send email. Phone lets user choose phone app to call.
		
##Wine Information Activity:
Loads image of label based on the wine selected from New Vintages, Regions, or All Wines fragments. All other content loaded from internal storage JSON file.

##Utilities:
Helper_JSONReader_Singleton:
- Used to load and read JSONObjects or JSONArrays from JSON files. 

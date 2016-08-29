# JGWines App for Android
A portfolio app for John Given Wines, Inc. displaying all their wines.


##Main Activity:
Uses 4 fragments to shift UI content by 4 buttons at top. Fragment changes are added to backstack, and back button also remembers button colors for previous tab selected.
4 Fragments:
   1. News Fragment:  
  -Tastings loaded from JSON file.  
  -New Vintages marked in JSON file and loaded.  
   2.  Regions Fragment:  
  -Regions loaded via RecyclerView.  
  -Wines in regions are RecyclerViews nested in Regions.
   3. All Wines Fragment:  
  -All wines loaded into RecyclerView.  
   4. About JGWines:  
  -Static content.
		
##Wine Information Activity:
Loads all content based on the wine details selected from New Vintages, Regions, or All Wines fragments. All content loaded from external JSON file.

##Utilities:
Helper_JSONReader_Singleton:
- Used to load and read JSONObjects or JSONArrays from JSON files. 
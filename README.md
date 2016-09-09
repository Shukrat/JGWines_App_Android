# John Given Wines, Inc. Wine Portfolio v1.1
### For Android
A portfolio app for John Given Wines, Inc. displaying all their wines. Setup in a way to allow modification to content of app without having to change code. Person doing maintenance (updating wines, regions, tastings, etc) does not code Android, so updating a simple JSON file will make it possible to keep a clean looking app up to date.  JSON files will also be used for the John Given Wines website to load content, thus making it possible to only update files in one location to update this app and the website.

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
  -Can search for wines by title, vineyard, and type (red, white, rose).
   4. About JGWines:  
  -Contact info: Email lets user choose email app to send email. Phone lets user choose phone app to call.

##Wine Information Activity:
Loads image of label based on the wine selected from New Vintages, Regions, or All Wines fragments. All other content loaded from internal storage JSON file.

##Utilities:
Helper_JSONReader_Singleton:
- Used to load and read JSONObjects or JSONArrays from JSON files.

Helper_ServerImageRequest:  
- Used to get wine label from server when entering Wine Information Activity.  
- If the server can't be reached, displays the JG Wines logo instead.  

Helper_ServerFileRequest:  
- Used in Initial Activity to first check versions, then download all JSON files if versions mismatch.  
- If server can't be reached, tells user via Toast, then loads previous available data on device.

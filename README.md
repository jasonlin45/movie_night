# movie_night

Uses IMDb API to choose movies. Can take user input (preferred genre, length of movie) into consideration when recommending movies. The user can also choose times they would like to watch movies.

Preference Package:

  Genre: contains Genre object attributes (id and name) that match with TMDB genre API result.

  GenreList: Wrapper to mimic JSON result that is received from get genre API result set.

  GenreService: Performs the following
    1. Reads API Key from apiKeyFileName using readAPIFileOnInternalStorage() function.
    2. Stars asynchronous task which communicates with TMDB genre API in a separate thread.
    3. After asynch thread is done executing, main thread receives JSON string and deserializes JSON to a list of Genre objects.
    4. Populates drop down menu

    Read and Write Functions to save and retrieve genre preferences and APIkey:
    - writeFileOnInternalStorage() function creates directory and writes to two different files: apiKeyFileName and storageFile variables
    - readFileOnInternalStorage(): Returns a list of all stored Genre objects stored by the user in storageFile variable
    - readAPIFileOnInternalStorage(): Returns a string with the APIkey stored in apiKeyFileName variable

  PreferenceViewModel: Used other view model files as reference.

  PreferenceFragment: Performs the following
    1. instantiates genreService object
    2. starts the asynchronous task to get the genre from TMDB

    - OnClick of "Save" button writes user preference genre chosen on drop down onto storageFile

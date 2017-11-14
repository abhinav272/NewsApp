# Android Architecture Sample

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) were announced in Google I/O 2017
This is just a sample app explaining the new Architecture Guidelines written in **Kotlin**.
This sample app is powered by [NewsAPI](https://newsapi.org/).

## Components Used
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
- [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room.html)

## About App
This app uses [NewsAPI](https://newsapi.org/) to get various sources and each source can provide major headlines.
It uses [Retrofit 2](http://square.github.io/retrofit/) to fetch news sources and news headlines from the API and displays in a RecyclerView.
It uses [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room.html) to provide offline functionality
App first loads the data from DB and then checks for fresh data from API, API is only called if DB data is empty or expired
The main aim of this sample app is show how to use the new [Architecture Guidelines](https://developer.android.com/topic/libraries/architecture/index.html) with Kotlin.

## If you want to run:
- Go to [NewsAPI](https://newsapi.org/) and generate an API key (It's only 2 steps!)
- Put the API key at the bottom of the `gradle.properties`
`
newsAPIKey = "YOUR_API_KEY"
`
- Run the app

## Architecture

The app uses `ViewModel` to abstract the data from UI and `Repository` as single source of truth for data. `Repository` fetch the data from database and shows, while also checks if the data is old. If the data is old or database does not contain any data (such as first launch) it fetches data from Web Service and saves the data into database.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

--------------------

## Screenshots

<img alt="NewsApp Main Page" height="450px" src="https://raw.githubusercontent.com/abhinav272/NewsApp/master/art/Screenshot_1509641380.png" />

<img alt="NewsApp Main Page" height="450px" src="https://raw.githubusercontent.com/abhinav272/NewsApp/master/art/Screenshot_1509641449.png" />

<img alt="NewsApp Main Page" height="450px" src="https://raw.githubusercontent.com/abhinav272/NewsApp/master/art/Screenshot_1509642715.png" />



--------------------

## Future Roadmap
- ~~Room Persistence Library for offline support~~
- Support for Launguage, Country and Category selection
- ~~Write test cases~~

<p align="center">
  <h3>Proudly :muscle: made in <b><a href="https://kotlinlang.org/">Kotlin</a></b></h3>
</p>

## License
-------

    The MIT License (MIT)
    
    Copyright (c) 2017 Abhinav Sharma
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

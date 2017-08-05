## Purpose
This tool is meant to automate the process of downloading TV Shows 
based on watch history from [trakt.tv](https://trakt.tv/)

## Requirements
* JDK 8
* Gradle
* account on [trakt.tv](https://trakt.tv/)

## Usage
TODO: add description how to use the tool

## How it works
This tool uses Selenium WebDriver to get a list of TV Shows 
from _'Up next to watch'_ section on [trakt.tv](https://trakt.tv/), 
searches them on TPB and initiates the download for you.
 
'Up next to watch' section on [trakt.tv](https://trakt.tv/) (not mine):
![](https://lh3.googleusercontent.com/11Ib3BS423Vk3gaiY-lpyErUZO0_Qx1LXpfltktaaDaLd7k4h-_dUPH0OwoDQt-_Bs7mjm-I=w640-h400-e365)

## TODO
* implement search functionality on TPB
* update 'Usage' section in this doc
* add tests
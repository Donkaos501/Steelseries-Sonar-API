# Steelseries Sonar API

This is an unofficial api in Java for the Sonar app.

**Disclaimer:** <br>
I am not a professional and there may be bugs and stuff.
<br>
This project would not be possible without the work of other who reversed engineered the api calls.
<br>
#### Repo's that reversed engineered the api:
 - https://github.com/wex/sonar-rev
 - https://github.com/TotalPanther317/steelseries-sonar-api
 - https://github.com/Mark7888/steelseries-sonar-py
 - https://github.com/SonarSource/sonar-plugin-api


### Features
 - Volumen control 
 - Mute channels
 - Change devices


### Usage
Java 8 or above!
<br>
This repo is on [jitpack.io](https://jitpack.io/#Donkaos501/Steelseries-Sonar-API)

#### Api setup:
```JAVA
SteelSeriesAPI steel_series_api = new SteelSeriesAPI();
SonarAPI sonar_api = new SonarAPI(steel_series_api);
 ```

#### Volumen control:
```JAVA
// Setting it to 20%
sonar_api.setVolume(Channel.CHAT,0.2);
sonar_api.setMute(Channel.CHAT,true);
 ```

#### Change device:
```JAVA
System.out.println("Output devices:");
sonar_api.getOutputDevices().forEach((key, value) -> {
    System.out.println(key + " " + value.getFriendlyName());
});

sonar_api.setOutput(Channel.CHAT,sonar_api.getOutputDevices().get("{0.0.0.00000000}.{b86360bb-c1f9-47ed-be6d-ccc4f8598c85}"));
 ```

#### Future plans:
I may be adding some more features if there is demand or i have the time for it.
 - Streamer mode
 - Microphone device change
 - Audio channel filters
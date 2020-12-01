<img src="https://static.mparticle.com/sdk/mp_logo_black.svg" width="280">

# Java SDK Sample
This Andorid app operates as a "proxy" Java backend using the [Java SDK](https://github.com/mParticle/mparticle-java-events-sdk)

On App load a batch will be sent S2S to mParticle. 

# Trouble shooting
- If you are not seeing the batch in Live Stream make sure the emulator you are using does not have any denied permissions. You can check this by navigating to Settings -> Apps -> Selecting your App
- Remember to put your API Key and Secret in `EventsApi api = new ApiClient("REPLACE ME", "REPLACE ME")`
- If the build is seeming to send "old data" update the name of the instance of the Thread on the Main Activity. i.e thread2 would become thread3

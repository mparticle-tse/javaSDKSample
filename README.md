# Java SDK Sample
This Andorid app operates as a "proxy" Java backend using the [Java SDK](https://github.com/mParticle/mparticle-java-events-sdk)

On App load a batch will be sent S2S to mParticle. 

# Trouble shooting
- If you are not seeing the batch in Live Stream make sure the emulator you are using does not have any denied permissions. You can check this by navigating to Settings -> Apps -> Selecting your App
- Remeber to put your API Key and Secret in `EventsApi api = new ApiClient("REPLACE ME", "REPLACE ME")`
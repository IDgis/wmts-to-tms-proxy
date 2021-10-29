# wmts-to-tms-proxy
*WMTS to TMS proxy*

The frontend of this proxy behaves like a TMS. The back-end talks to a WMTS. Use this proxy to talk to a WMTS like if it were a TMS.

# Installatie
Kopieer de jar + config map naar ecw0 (cloud1) -> ~/tms-wmts-proxy (met behulp van winscp of scp)  
Pas de versie aan naar de juiste jar in run_wtms-to-tms-proxy.sh  

Herstart de service met de nieuwe versie door:
```shell
sudo systemctl restart wmts-to-tms-proxy.service
```

Bekijk de logging met:
```shell
journalctl -f -u wmts-to-tms-proxy.service
```

# Laag URL aanpassen
In de map ``config/tileMapResourceFiles`` staan alle configuratie bestanden voor de verschillende lagen.
In het *.properties bestand staan een property ``baseUrl`` en ``layerName``. Pas deze naar behoefte aan.

# Laag toevoegen aan de wmts-to-tms-proxy
Maak in de map ``config/tileMapResourceFiles`` de capabilities xml en properties bestand aan voor de nieuw toe te voegen
laag. De bestandsnaam wordt gebruikt in URL van de wmts-to-tms-proxy. Voeg de config voor de nieuwe laag vervolgens
nog toe in het algemene capabilities document in ``config/Capabilities/Capabilities.xml``.

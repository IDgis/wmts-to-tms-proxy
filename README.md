# wmts-to-tms-proxy
*WMTS to TMS proxy*

The frontend of this proxy behaves like a TMS. The back-end talks to a WMTS. Use this proxy to talk to a WMTS like if it were a TMS.

# Installatie
Kopieer de jar naar cloud0 -> ~/tms-wmts-proxy (met behulp van winscp of scp)  
Pas de versie aan naar de juiste jar in run_wtms-to-tms-proxy.sh  

Herstart de service met de nieuwe versie door:
```
sudo systemctl restart wmts-to-tms-proxy.service
```

Bekijk de logging met:
```
journalctl -f -u wmts-to-tms-proxy.service
```

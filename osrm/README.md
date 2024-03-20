関東地方の（多分車の）経路データがkantoファイルの中に入っている


kantoファイル内のデータの作り方
```
mkdir kanto
wget -P ./kanto https://download.geofabrik.de/asia/japan/kanto-latest.osm.pbf
docker run -t -v "${PWD}/kanto/:/data" ghcr.io/project-osrm/osrm-backend osrm-extract -p /opt/car.lua /data/kanto-latest.osm.pbf || echo "osrm-extract failed"
docker run -t -v "${PWD}/kanto/:/data" ghcr.io/project-osrm/osrm-backend osrm-partition /data/kanto-latest.osrm || echo "osrm-partition failed"
docker run -t -v "${PWD}/kanto/:/data" ghcr.io/project-osrm/osrm-backend osrm-customize /data/kanto-latest.osrm || echo "osrm-customize failed"
```

参考
https://github.com/Project-OSRM/osrm-backend
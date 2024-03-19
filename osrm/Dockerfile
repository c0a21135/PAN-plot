FROM ghcr.io/project-osrm/osrm-backend

COPY kanto/ /data/

CMD [ "osrm-routed", "--algorithm", "mld", "/data/kanto-latest.osrm" ]
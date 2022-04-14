# QFlock

# One time setup

```shell
git submodule  init
git submodule update --recursive --progress
```

# build
```shell
./build.sh
```
# start
```shell
./start.sh
```
# stop
```shell
./stop.sh
```
# clean<BR>
to clean out build artifacts
```shell
./clean.sh
```
# clean all<BR>
cleans out build artifacts and all other artifacts including dockers and downloads
```shell
./clean_all.sh
```
# clean all but skip docker removal
```shell
NO_CLEAN_DOCKERS=1 ./clean_all.sh
```

# reinitialize
Use these commands to stop services, remove all artifacts and rebuild the repo.
```shell
./stop.sh && ./clean.sh && ./build.sh
```

# Configuring and running benchmarks.
```shell
# Examples
- Initialize benchmark completely
./init.sh

- Run all benchmark queries
benchmark/src/docker-bench.py --queries "*"

- Get help on available switches
benchmark/src/docker-bench.py --help
```


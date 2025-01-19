#!/usr/bin/env bash

sha256() {
  sha256sum "$1" | awk '{print $1}'
}

sha1() {
  sha1sum "$1" | awk '{print $1}'
}

md5() {
  md5sum "$1" | awk '{print $1}'
}

prop() {
  grep "${1}" gradle.properties | cut -d'=' -f2 | sed 's/\r//'
}

commitid=$(git log --pretty='%h' -1)
mcversion=$(prop mcVersion)
version=$(prop version)
experimental=$(prop experimental)
tagid="$mcversion-$BUILD_NUMBER-$commitid"
jarName="divinemc-$mcversion-$BUILD_NUMBER.jar"
divinemcid="DivineMC-$tagid"
changelog="changelog.md"
make_latest=$([ "$experimental" = "true" ] && echo "false" || echo "true")

rm -f $changelog

mv divinemc-server/build/libs/divinemc-paperclip-"$version"-mojmap.jar "$jarName"
{
  echo "name=$divinemcid"
  echo "tag=$tagid"
  echo "jar=$jarName"
  echo "info=$changelog"
  echo "experimental=$experimental"
  echo "make_latest=$make_latest"
} >> "$GITHUB_ENV"

{
  echo "$divinemcid [![download](https://img.shields.io/github/downloads/DivineMC/DivineMC/$tagid/total?color=0)](https://github.com/DivineMC/DivineMC/releases/download/$tagid/$jarName)"
  echo "====="
  echo ""
  if [ "$experimental" = "true" ]; then
    echo "> [!WARNING]"
    echo "> This is an early, experimental build. It is only recommended for usage on test servers and should be used with caution."
    echo "> **Backups are mandatory!**"
    echo ""
  fi
  echo "### Commits"
} >> $changelog

number=$(git log --oneline master ^"$(git describe --tags --abbrev=0)" | wc -l)
git log --pretty='> [%h] %s' "-$number" >> $changelog

{
  echo ""
  echo "### Checksum"
  echo "| File | $jarName |"
  echo "| ---- | ---- |"
  echo "| MD5 | $(md5 "$jarName") |"
  echo "| SHA1 | $(sha1 "$jarName") |"
  echo "| SHA256 | $(sha256 "$jarName") |"
} >> $changelog


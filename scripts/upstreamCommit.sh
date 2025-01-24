#!/usr/bin/env bash

# requires curl & jq

# upstreamCommit <old hash>

function getCommits() {
    curl -H "Accept: application/vnd.github.v3+json" https://api.github.com/repos/"$1"/compare/"$2"...HEAD | jq -r '.commits[] | "'"$1"'@\(.sha[:8]) \(.commit.message | split("\r\n")[0] | split("\n")[0])" | sub("\\[ci( |-)skip]"; "[ci/skip]")'
}

(
set -e
PS1="$"

# Purpur updates
purpurHash="$1"
purpur=$(getCommits "PurpurMC/Purpur" "$purpurHash")
updated="Purpur"
logsuffix="\n\nPurpur Changes:\n$purpur"

disclaimer="Upstream has released updates that appear to apply and compile correctly"
log="Updated Upstream ($updated)\n\n${disclaimer}${logsuffix}"

echo -e "$log" | git commit -F -

) || exit 1

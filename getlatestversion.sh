#! /bin/bash
export lastKnownVersion=`yq e '.initializr.env.boms.axon-bom.mappings[0].version' src/main/resources/application.yaml`
export latestVersion=`curl -s https://search.maven.org/classic/solrsearch/select?q=g:org.axonframework+AND+a:axon-bom | jq -r '.response.docs[0].latestVersion'`
echo $lastKnownVersion
echo $latestVersion

if [ $lastKnownVersion != $latestVersion ]
then
  yq e '.initializr.env.boms.axon-bom.mappings[0].version=env(latestVersion)' -i src/main/resources/application.yaml
fi

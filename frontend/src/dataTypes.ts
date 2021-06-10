import { readable } from 'svelte/store';

export interface InitializerData {
    _links:       DataLinks;
    dependencies: DataDependencies;
    
    type:         DataType;
    packaging:    SettingsItem;
    javaVersion:  SettingsItem;
    language:     SettingsItem;
    bootVersion:  SettingsItem;

    groupId:      MetadataItem;
    artifactId:   MetadataItem;
    version:      MetadataItem;
    name:         MetadataItem;
    description:  MetadataItem;
    packageName:  MetadataItem;
}

export interface DataLinks {
    "maven-project":  DataLinkItem;
    "maven-build":    DataLinkItem;
    "gradle-project": DataLinkItem;
    "gradle-build":   DataLinkItem;
    dependencies:     DataLinkItem;
}

export interface DataLinkItem {
    href:       string;
    templated?: boolean;
}

export interface MetadataItem {
    type:    string;
    default: string;
}

export interface SettingsItem {
    type:    string;
    default: string;
    values:  SettingsItemValue[];
}

export interface SettingsItemValue {
    id:   string;
    name: string;
}

export interface DataDependencies {
    type:   string;
    values: DependenciesValueWithName[];
}

export interface DependenciesValueWithName {
    name:   string;
    values: DependenciesValue[];
}

export interface DependenciesValue {
    id:            string;
    name:          string;
    description:   string;
    _links?:       ValueLinks;
    versionRange?: string;
}

export interface ValueLinks {
    "axon-starter"?:      ValueLinkItem;
    docs?:                ValueLinkItem;
    "video-guide"?:       ValueLinkItem;
    "github-repository"?: ValueLinkItem;
    guide?:               ValueLinkItem[] | ValueLinkItem;
    reference?:           DataLinkItem;
    home?:                ValueLinkItem;
}

export interface ValueLinkItem {
    href:  string;
    title: string;
}

export interface DataType {
    type:    string;
    default: string;
    values:  TypeValue[];
}

export interface TypeValue {
    id:          string;
    name:        string;
    description: string;
    action:      string;
    tags:        Tags;
}

export interface Tags {
    build:  string;
    format: string;
}

async function getData(): Promise<InitializerData> {
    const response = await fetch("http://localhost:8080/");
    if (!response.ok) {
        throw new Error('Bad response');
    }
    return await response.json();
}

export const data = readable<InitializerData | null>(null, (set) => {
    getData()
        .then(set)
        .catch(err => {
            console.error("Failed to fetch", err);
        });
});

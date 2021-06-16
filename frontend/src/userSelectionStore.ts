import { writable } from 'svelte/store';

export const userSelection = writable({
    // Metadata
    groupId: '',
    artifactId: '',
    name: '',
    description: '',
    packageName: '',

    // Settings
    type: '',
    language: '',
    javaVersion: '',
    packaging: '',
    
    // Dependencies
    dependencies: [],

    // Hardcoded
    baseDir: '',
});

<script lang="ts">
import { onMount } from "svelte";
import ActionFooter from "./components/ActionFooter/ActionFooter.svelte";
import Footer from "./components/Footer/Footer.svelte";
import Header from "./components/Header/Header.svelte";
import HomeDependencies from "./components/HomeDependencies/HomeDependencies.svelte";
import HomeMetadata from "./components/HomeMetadata/HomeMetadata.svelte";
import HomeSettings from "./components/HomeSettings/HomeSettings.svelte";
import type { DependenciesValue,InitializerData } from "./dataTypes";
import { getInitialData } from "./services/getInitialData";
import { userSelection } from './userSelectionStore';

let dataLoaded = false;
let initialData: InitializerData;
let addedDependencies: DependenciesValue[] = [];

onMount(async() => {
	initialData = await getInitialData();

	const urlSearchParams = new URLSearchParams(window.location.search);
	const params = Object.fromEntries(urlSearchParams.entries());
	history.replaceState && history.replaceState(null, '', location.pathname);

	userSelection.set({
		groupId: params.groupId || initialData.groupId.default,
		artifactId: params.artifactId || initialData.artifactId.default,
		baseDir: params.baseDir || initialData.artifactId.default,
		name: params.name || initialData.name.default,
		description: params.description || initialData.description.default,
		packageName: params.packageName || initialData.packageName.default,

		type: params.type || initialData.type.default,
		language: params.language || initialData.language.default,
		javaVersion: params.javaVersion || initialData.javaVersion.default,
		packaging: params.packaging || initialData.packaging.default,

		dependencies: (params.dependencies && params.dependencies.split(',')) || [],
	});
	let allDependencies = [];
	initialData.dependencies.values.forEach(depWithName => allDependencies = [...allDependencies, ...depWithName.values]);
	addedDependencies = allDependencies.filter(dep => $userSelection.dependencies.indexOf(dep.id) > -1);

	dataLoaded = true;
});

$: $userSelection.baseDir = $userSelection.artifactId;
$: $userSelection.dependencies = addedDependencies.map(dep => dep.id);
</script>

{#if !dataLoaded}
	<div>Loading...</div>
{:else}
	<div class="app">
		<header>
			<Header />
		</header>
		<main>
			<section class="app__content">
				<div>
					<HomeMetadata
						bind:groupId={$userSelection.groupId}
						bind:artifactId={$userSelection.artifactId}
						bind:name={$userSelection.name}
						bind:description={$userSelection.description}
						bind:packageName={$userSelection.packageName}
					/>
					<HomeSettings
						projectTypeData={initialData.type}
						projectLanguageData={initialData.language}
						projectJavaVersionData={initialData.javaVersion}
						projectPackagingData={initialData.packaging}

						bind:projectTypeSelected={$userSelection.type}
						bind:projectLanguageSelected={$userSelection.language}
						bind:projectJavaVersionSelected={$userSelection.javaVersion}
						bind:projectPackagingSelected={$userSelection.packaging}
					/>
				</div>
				<div>
					<HomeDependencies
						dependencyData={initialData.dependencies}

						bind:addedDependencies={addedDependencies}
					/>
				</div>
			</section>
			<div class="app__content-footer">
				<Footer />
			</div>
		</main>
		<footer>
			<section class="app__action-footer">
				<ActionFooter />
			</section>
		</footer>
	</div>
{/if}

<style type="scss">
	@use "./components/Colors/colors.scss";

	// Heavily relied on https://css-tricks.com/how-to-use-css-grid-for-sticky-headers-and-footers/ for building the grid
	.app {
		height: 100vh;
		background-image: url(/assets/grid-bg.svg);
		background-repeat: no-repeat;
		background-color: colors.$seal;
		background-position: 60vw 9vh;

		display: grid;
		grid-template-columns: 1fr;
		grid-template-rows: auto 1fr auto;
		grid-template-areas: 
			'header'
			'main'
			'action-footer';

		header {
			grid-area: header;
			grid-column: 1 / span 4;
		}
		
		main {
			grid-area: main;
			overflow: auto;

			display: grid;
			grid-template-columns: minmax(20px, 1fr) fit-content(1024px) minmax(20px, 1fr);
			grid-template-rows: 1fr auto;
		}
		.app__content {
			margin-top: 26px;
			margin-bottom: 26px;
			grid-area: 1 / 2 / 1 / 3;
			
			display: grid;
			grid-template-rows: 1fr;
			grid-template-columns: fit-content(320px) minmax(auto, 672px);
			grid-gap: 32px;
		}
		.app__content-footer {
			grid-area: 2 / 1 / 2 / 4;
		}

		footer {
			grid-area: action-footer;
			
			display: grid;
			grid-template-columns: minmax(20px, 1fr) minmax(auto, 1024px) minmax(20px, 1fr);
			background-color: colors.$dove;
			height: 100px;
			align-items: center;
			box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.4), 0px 5px 4px rgba(0, 0, 0, 0.1);
			border-radius: 4px;
			z-index: 1;
		}
		.app__action-footer {
			grid-area: 1 / 2 / 1 / 3;
		}

	}
</style>
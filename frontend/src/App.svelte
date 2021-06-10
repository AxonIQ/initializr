<script lang="ts">
import ActionFooter from "./components/ActionFooter/ActionFooter.svelte";
import Footer from "./components/Footer/Footer.svelte";
import Header from "./components/Header/Header.svelte";
import HomeDependencies from "./components/HomeDependencies/HomeDependencies.svelte";
import HomeMetadata from "./components/HomeMetadata/HomeMetadata.svelte";
import HomeSettings from "./components/HomeSettings/HomeSettings.svelte";
import type { InitializerData } from "./dataTypes";

async function preloadData(): Promise<InitializerData> {
    const response = await fetch("http://localhost:8080/");
    if (!response.ok) {
        throw new Error('Bad response');
    }
    return await response.json();
}

</script>
{#await preloadData()}
	<div>Loading...</div>
{:then data}
	<div class="app">
		<header>
			<Header />
		</header>
		<main>
			<section class="app__content">
				<div>
					<HomeMetadata
						groupId={data.groupId.default}
						artifactId={data.artifactId.default}
						name={data.name.default}
						description={data.description.default}
						packageName={data.packageName.default}
					/>
					<HomeSettings
						projectTypeData={data.type}
						projectLanguageData={data.language}
						projectAxonVersionData={data.bootVersion}
						projectJavaVersionData={data.javaVersion}
					/>
				</div>
				<div>
					<HomeDependencies />
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
{/await}

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
			grid-template-columns: fit-content(320px) 1fr;
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
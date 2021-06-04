<script lang="ts">
import Header from "./components/Header/Header.svelte";
import Footer from "./components/Footer/Footer.svelte";
import HomeMetadata from "./components/HomeMetadata/HomeMetadata.svelte";
import HomeSettings from "./components/HomeSettings/HomeSettings.svelte";
import HomeDependencies from "./components/HomeDependencies/HomeDependencies.svelte";
</script>
<div class="app">
	<header>
		<section class="app__header">
			<Header />
		</section>
	</header>
	<main>
		<section class="app__content">
			<div>
				<HomeMetadata />
				<HomeSettings />
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
		<section class="full">
			<!-- <Footer /> -->
		</section>
	</footer>
</div>
<style type="scss">
	@use "./components/Colors/colors.scss";

	// Heavily relied on https://css-tricks.com/how-to-use-css-grid-for-sticky-headers-and-footers/ for building the grid
	.app {
		height: 100vh;
		background-image: url(/assets/grid-bg.svg);
		background-repeat: no-repeat;
		background-color: colors.$seal;
		background-position: 1300px 85px;

		display: grid;
		grid-template-columns: 1fr;
		grid-template-rows: auto 1fr auto;
		grid-template-areas: 
			'header'
			'main'
			'action-footer';

		header {
			grid-area: header;
		}
		main {
			grid-area: main;
			overflow: auto;

			display: grid;
			grid-template-columns: 1fr 1024px 1fr;
			grid-template-rows: 1fr auto;
		}
		footer {
			grid-area: action-footer;
		}
		
		.app__content {
			margin-top: 26px;
			margin-bottom: 26px;
			grid-area: 1 / 2 / 1 / 3;
			
			display: grid;
			grid-template-rows: 1fr;
			grid-template-columns: auto 1fr;
			grid-gap: 32px;
			padding-left: 10px;
			padding-right: 10px;
		}
		.app__header {
			grid-area: 1 / 1 / 1 / 4;
		}
		.app__content-footer {
			grid-area: 2 / 1 / 2 / 4;
		}

	}
</style>
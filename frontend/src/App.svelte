<script lang="ts">
import Button from "./components/Button/Button.svelte";
import Radio from "./components/Radio/Radio.svelte";
import Colors from "./components/Colors/Colors.svelte";
import Typography from "./components/Typography/Typography.svelte";
import Card from "./components/Card/Card.svelte";
import IconButton from "./components/IconButton/IconButton.svelte";

let group1 = "";
let group2 = "Java";
let group3 = "Snapshot";

</script>

<main>
	<div class="app_row">
		<Colors />
	</div>

	<div class="app_row">
		<hr />
		<Typography size="s" center>Hello from small typography - 14px</Typography>
		<Typography size="s" center weight="bold">Hello from small typography - 14px</Typography>
		<Typography size="s" center italic>Hello from small typography - 14px</Typography>
		<hr />
		<Typography size="m" center>Hello from medium typography - 16px</Typography>
		<Typography size="m" center weight="bold">Hello from medium typography - 16px</Typography>
		<Typography size="m" center italic>Hello from medium typography - 16px</Typography>
		<hr />
		<Typography size="l" center>Hello from large typography - 18px</Typography>
		<Typography size="l" center weight="bold">Hello from large typography - 18px</Typography>
		<Typography size="l" center italic>Hello from large typography - 18px</Typography>
		<hr />
		<Typography size="xl" center>Hello from extra large typography - 24px</Typography>
		<Typography size="xl" center weight="bold">Hello from extra large typography - 24px</Typography>
		<Typography size="xl" center italic>Hello from extra large typography - 24px</Typography>
		<hr />
	</div>

	<div class="app_row" style="display: flex; justify-content: center;">
		<div class="app__button-wrapper">
			<Button onClick={() => alert('Hello from a secondary button')} variant="secondary" text="Use cases" />
		</div>
		<div class="app__button-wrapper">
			<Button variant="secondary" disabled text="Use cases" />
		</div>
	</div>
	<div class="app_row" style="display: flex; justify-content: center;">
		<div class="app__button-wrapper">
			<Button onClick={() => alert('Hello from a primary button')} text="Use cases" />
			</div>
			<div class="app__button-wrapper">
				<Button text="Use cases" disabled />
			</div>
		</div>
	<hr />
	<div class="app_row" style="display: flex; flex-direction: column; align-items: center;">
		<div class="app_row" style="display: flex; align-items: center;">
			<div style="margin-right: 30px;">
				<Radio value="Maven" label="Maven" bind:group={group1} name="group1" />
				<Radio value="Gradle" label="Gradle" bind:group={group1} name="group1" />
				<Typography>Selected: <Typography inline weight="bold">{group1}</Typography></Typography>
			</div>
			<div style="margin-right: 30px;">
				<Radio value="Java" label="Java" bind:group={group2} name="group2"/>
				<Radio value="Java & Kotlin" label="Java & Kotlin" bind:group={group2} name="group2"/>
				<Typography>Selected: <Typography inline weight="bold">{group2}</Typography></Typography>
			</div>
			<div style="margin-right: 30px;">
				<Radio value="Latest" label="Latest" bind:group={group3} name="group3"/>
				<Radio value="Snapshot" label="Snapshot" bind:group={group3} name="group3"/>
				<Typography>Selected: <Typography inline weight="bold">{group3}</Typography></Typography>
			</div>
		</div>
	</div>
	<hr />
	<div class="app_row" style="display: flex; flex-direction: row; justify-content: center;">
		<div style="margin-right: 30px;">
			<IconButton>
				<span class="icon-wrapper">
					<svg width="17" height="19" viewBox="0 0 17 19" fill="none" xmlns="http://www.w3.org/2000/svg">
						<path d="M16.1446 1.375H11.9258L11.5743 0.742188C11.4336 0.460938 11.1524 0.25 10.836 0.25H6.79301C6.4766 0.25 6.19535 0.460938 6.05473 0.742188L5.73832 1.375H1.51957C1.20317 1.375 0.957073 1.65625 0.957073 1.9375V3.0625C0.957073 3.37891 1.20317 3.625 1.51957 3.625H16.1446C16.4258 3.625 16.7071 3.37891 16.7071 3.0625V1.9375C16.7071 1.65625 16.4258 1.375 16.1446 1.375ZM2.82035 16.668C2.85551 17.582 3.59379 18.25 4.50785 18.25H13.1211C14.0352 18.25 14.7735 17.582 14.8086 16.668L15.5821 4.75H2.08207L2.82035 16.668Z" fill="white"/>
					</svg>
				</span>
			</IconButton>
		</div>
		<div style="margin-right: 30px;">
			<IconButton color="peacock">
				<svg width="16" height="17" viewBox="0 0 16 17" fill="none" xmlns="http://www.w3.org/2000/svg">
					<path d="M14.7619 6.5625H9.69942V1.5C9.69942 0.902344 9.17208 0.375 8.57442 0.375H7.44942C6.81661 0.375 6.32442 0.902344 6.32442 1.5V6.5625H1.26192C0.62911 6.5625 0.136923 7.08984 0.136923 7.6875V8.8125C0.136923 9.44531 0.62911 9.9375 1.26192 9.9375H6.32442V15C6.32442 15.6328 6.81661 16.125 7.44942 16.125H8.57442C9.17208 16.125 9.69942 15.6328 9.69942 15V9.9375H14.7619C15.3596 9.9375 15.8869 9.44531 15.8869 8.8125V7.6875C15.8869 7.08984 15.3596 6.5625 14.7619 6.5625Z" fill="white"/>
				</svg>
 			</IconButton>
		</div>
		<IconButton color="seal">
			<svg width="17" height="19" viewBox="0 0 17 19" fill="none" xmlns="http://www.w3.org/2000/svg">
				<path d="M16.1446 1.375H11.9258L11.5743 0.742188C11.4336 0.460938 11.1524 0.25 10.836 0.25H6.79301C6.4766 0.25 6.19535 0.460938 6.05473 0.742188L5.73832 1.375H1.51957C1.20317 1.375 0.957073 1.65625 0.957073 1.9375V3.0625C0.957073 3.37891 1.20317 3.625 1.51957 3.625H16.1446C16.4258 3.625 16.7071 3.37891 16.7071 3.0625V1.9375C16.7071 1.65625 16.4258 1.375 16.1446 1.375ZM2.82035 16.668C2.85551 17.582 3.59379 18.25 4.50785 18.25H13.1211C14.0352 18.25 14.7735 17.582 14.8086 16.668L15.5821 4.75H2.08207L2.82035 16.668Z" fill="#434A56"/>
			</svg>				
		</IconButton>
	</div>
	<hr />
	<div class="app_row" style="display: flex; flex-direction: column; align-items: center;">
		<div style="max-width: 672px;">
			<Card>
				<div style="margin-bottom: 12px;">
					<Typography size="m" weight="bold" removeMargin>Title</Typography>
				</div>
				<Typography size="s">
					Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
				</Typography>
			</Card>
		</div>
	</div>
</main>

<style type="scss">
	main {
		padding: 1em;
		margin: 0 auto;
	}
	.app_row {
		margin-bottom: 20px;
		margin-top: 20px;
	}
	.app__button-wrapper {
		margin-right: 20px;
	}
	// .trash-icon {
	// 	background-image: url(/assets/trash.svg);
	// 	background-repeat: no-repeat;
	// 	width: 24px;
	// 	height: 24px;
	// }
</style>
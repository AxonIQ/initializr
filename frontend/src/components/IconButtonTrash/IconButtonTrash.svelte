<script lang="ts">
    import IconButton from "../IconButton/IconButton.svelte";
    
    export let disabled = false;
    export let onClick = () => {}; 
</script>
<IconButton
    disabled={disabled}
    onClick={() => onClick()}>
    <svg width="17" height="19" viewBox="0 0 17 19" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M16.1446 1.375H11.9258L11.5743 0.742188C11.4336 0.460938 11.1524 0.25 10.836 0.25H6.79301C6.4766 0.25 6.19535 0.460938 6.05473 0.742188L5.73832 1.375H1.51957C1.20317 1.375 0.957073 1.65625 0.957073 1.9375V3.0625C0.957073 3.37891 1.20317 3.625 1.51957 3.625H16.1446C16.4258 3.625 16.7071 3.37891 16.7071 3.0625V1.9375C16.7071 1.65625 16.4258 1.375 16.1446 1.375ZM2.82035 16.668C2.85551 17.582 3.59379 18.25 4.50785 18.25H13.1211C14.0352 18.25 14.7735 17.582 14.8086 16.668L15.5821 4.75H2.08207L2.82035 16.668Z" fill="white"/>
    </svg>
</IconButton>
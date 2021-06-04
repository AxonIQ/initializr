<script lang="ts">
    import IconButton from "../IconButton/IconButton.svelte";
    
    export let disabled = false;
    export let onClick = () => {}; 
</script>
<IconButton
    disabled={disabled}
    color="peacock"
    onClick={() => onClick()}>
    <svg width="16" height="17" viewBox="0 0 16 17" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path d="M14.7619 6.5625H9.69942V1.5C9.69942 0.902344 9.17208 0.375 8.57442 0.375H7.44942C6.81661 0.375 6.32442 0.902344 6.32442 1.5V6.5625H1.26192C0.62911 6.5625 0.136923 7.08984 0.136923 7.6875V8.8125C0.136923 9.44531 0.62911 9.9375 1.26192 9.9375H6.32442V15C6.32442 15.6328 6.81661 16.125 7.44942 16.125H8.57442C9.17208 16.125 9.69942 15.6328 9.69942 15V9.9375H14.7619C15.3596 9.9375 15.8869 9.44531 15.8869 8.8125V7.6875C15.8869 7.08984 15.3596 6.5625 14.7619 6.5625Z" fill="white"/>
    </svg>
</IconButton>
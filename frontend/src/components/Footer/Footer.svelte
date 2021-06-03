<script>
    import GradientDivider from "../GradientDivider/GradientDivider.svelte";
    import Typography from "../Typography/Typography.svelte";
    
    </script>
        <GradientDivider />
        <div class="footer">
            <div class="footer__content">
                <div class="footer__content-logos">
                    <img class="footer__academy-logo" src="/assets/logo-academy.png" alt="Logo" />
                    <img src="/assets/logo-axoniq.png" alt="Logo" />
                </div>
                <div class="footer__banner">
                    <Typography size="l" weight="bold">Banner here</Typography>
                </div>
            </div>
        </div>
    <style lang="scss">
        @use "../Colors/colors.scss";
    
        .footer {
            background-color: colors.$dove;
            display: flex;
            justify-content: center;
            padding-top: 10px;
            height: 100px;
        }
        .footer__content {
            display: flex;
            width: 100%;
            justify-content: space-between;
            align-items: center;
            max-width: 1024px;
        }
        .footer__content-logos {
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .footer__academy-logo {
            margin-right: 40px;
        }
        .footer__banner {
            width: 320px;
            height: 80px;
            background-color: colors.$seal;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
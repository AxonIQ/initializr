<script>
    import GradientDivider from "../GradientDivider/GradientDivider.svelte";
    import Typography from "../Typography/Typography.svelte";
    
    </script>

        <GradientDivider />
        <div class="footer">
            <div class="footer__content">
                <div class="footer__content-logos">
                    <img src="/assets/logo-academy.png" alt="Logo" />
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
            display: grid;
            grid-template-columns: minmax(20px, 1fr) minmax(auto, 1024px) minmax(20px, 1fr);
			background-color: colors.$dove;

            justify-content: center;
            height: 100px;
            padding: 10px 0;
        }
        .footer__content {
            grid-column: 2;
            
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .footer__content-logos {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 40px;
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
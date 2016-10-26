<!DOCTYPE html>
<html>
<head>
	<title>DEMO</title>
</head>
<style type="text/css">
	*{
		margin: 0;
		padding: 0;
	}
	.main{
		margin: 10px auto;
		position: relative;
		width: 70%;
	}
	.bg{
		position: relative;
		background-image: url("res/img/bg.png");
		background-repeat: no-repeat;
		height: 600px;
		width: 35%;
		margin:0 auto;
	}
	.bg .element{
		position: absolute;
		top:${top}px;
		left: ${left}px;
		width: ${w}px;
		height: ${h}px;
		opacity: ${opacity};
	}
	.bg .element img{
		width: 100%;
		height: 100%;
	}
	.moveDown{
		animation-name: moveDown;
		-webkit-animation-name: moveDown;	
	
		animation-duration: 1s;	
		-webkit-animation-duration: 1s;
	
		animation-timing-function: ease;	
		-webkit-animation-timing-function: ease;	
	
		visibility: visible !important;						
	}

	@keyframes moveDown {
		0% {
			transform: translateY(-100%);
		}
			
		100% {
			transform: translateY(0%);
		}		
	}

	@-webkit-keyframes moveDown {
		0% {
			-webkit-transform: translateY(-100%);
		}
			
		100% {
			-webkit-transform: translateY(0%);
		}	
	}
</style>
<body>
	<section class="main">
		<div class="bg">
			<div class="element moveDown">
				<img src="img/head.jpg">	
			</div>
		</div>
	</section>
</body>
</html>

precision mediump float;

varying vec2 pass_TextureCoords;

uniform sampler2D textureSampler;

void main(void){

	vec4 textureColor = texture2D(textureSampler, pass_TextureCoords);
	if(textureColor.a < 0.5){
		discard;
	}

	gl_FragColor = textureColor;

}
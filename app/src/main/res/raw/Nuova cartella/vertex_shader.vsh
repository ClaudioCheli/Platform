precision mediump float;

attribute vec3 position;
attribute vec2 textureCoords;

varying vec2 pass_TextureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform float numberOfRows;
uniform vec2 offset;

void main(void){

	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);

	pass_TextureCoords = (textureCoords/numberOfRows) + offset;

}
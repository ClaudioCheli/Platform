#version 300 es
precision mediump float;
in vec2 texCoordinate;
in vec4 myColor;

uniform sampler2D myTexture;

out vec4 color;

void main() {

    vec4 texColor = texture(myTexture, texCoordinate);
    if(texColor == vec4(texColor.x,texColor.y,texColor.z,0))
        discard;

    color = texColor.w * myColor;

}
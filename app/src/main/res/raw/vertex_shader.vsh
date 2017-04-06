#version 300 es

in vec3 vertexPosition;
in vec2 texCoord;

uniform mat4 projection;

void main(){
gl_Position = projection * vec4(vertexPosition.x, vertexPosition.y, vertexPosition.z -10.0, 1.0);
}
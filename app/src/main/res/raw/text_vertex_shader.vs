#version 300 es
layout (location = 0) in vec4 in_position;
layout (location = 1) in vec2 in_texCoordinate;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
uniform vec4 u_color;

out vec2 texCoordinate;
out vec4 myColor;

void main() {

    texCoordinate = in_texCoordinate;
    myColor = u_color;
    gl_Position = projection * model * vec4(in_position.x, in_position.y, 0, in_position.w);

}


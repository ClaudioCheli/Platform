#version 300 es
layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec2 texCoord;

out vec2 texCoordinate;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
uniform int textureIndex;
uniform int tilesetNumberOfRows;
uniform int tilesetNumberOfColumns;

void main(){	
 	gl_Position = projection * view * model * vec4(vertexPosition.x, vertexPosition.y, vertexPosition.z -10.0, 1.0);

    float texID             = float(textureIndex-1);
    float numberOfColumns   = float(tilesetNumberOfColumns);
    float numberOfRows      = float(tilesetNumberOfRows);

    float column  	 = mod( texID, numberOfColumns);
    float row     	 = floor(texID / numberOfRows);
    vec2 offset 	 = vec2(column/numberOfColumns, row/numberOfRows);
    texCoordinate	 = vec2( vec2(texCoord.x/numberOfColumns, texCoord.y/numberOfRows) + offset );
}
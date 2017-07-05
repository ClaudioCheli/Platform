#version 300 es

layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec2 texCoord;
layout (location = 2) in vec3 tilePosition;
layout (location = 3) in int textureIndex;

out vec2 texCoordinate;
flat out int draw;

uniform mat4 view;
uniform mat4 projection;
uniform int tilesetNumberOfRows;
uniform int tilesetNumberOfColumns;

void main(){	

    gl_Position = projection * view * vec4(vertexPosition.x + tilePosition.x,
                                    vertexPosition.y + tilePosition.y,
                                    vertexPosition.z + tilePosition.z, 1.0);

    //gl_Position = projection * vec4(vertexPosition.x, vertexPosition.y, vertexPosition.z-10, 1.0);

    float texID             = float(textureIndex-1);
    float numberOfColumns   = float(tilesetNumberOfColumns);
    float numberOfRows      = float(tilesetNumberOfRows);
    if(textureIndex != 0){
    	float column  	 = mod(texID, numberOfColumns);
    	float row     	 = floor((texID) / numberOfRows);
    	vec2 offset 	 = vec2(column/numberOfColumns, row/numberOfRows);
    	texCoordinate	 = vec2( vec2(texCoord.x/numberOfColumns, texCoord.y/numberOfRows) + offset );
    	draw = 1;
    } else {
    	draw = 0;
    }
}
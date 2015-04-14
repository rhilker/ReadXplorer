/*
 * Copyright (C) 2015 Oliver Schwengers <oliver.schwengers@computational.bio.uni-giessen.de
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.cebitec.readxplorer.api.enums;


/**
 * Global enumeration for all strand related information.
 *
 * @author Oliver Schwengers <oliver.schwengers@computational.bio.uni-giessen.de
 */
public enum RegionType {


    /**
     * Type value identifying an object as belonging to any of the other types.
     */
    All( 0, "All" ),

    /**
     * Type value identifying an object as belonging to a "Start".
     */
    Start( 1, "Start" ),

    /**
     * Type value identifying an object as belonging to a "Stop".
     */
    Stop( 2, "Stop"),

    /**
     * Type value identifying an object as belonging to a "pattern".
     */
    Pattern( 3, "Pattern"),

    /**
     * Type value identifying an object as belonging to a "CDS" = coding
     * sequence.
     */
    CDS( 4, "CDS");


    private final int typeInt;
    private final String typeString;


    private RegionType( int typeInt, String typeString ) {
        this.typeInt = typeInt;
        this.typeString = typeString;
    }


    public int getType() {
        return typeInt;
    }


    @Override
    public String toString() {
        return typeString;
    }


    public static RegionType fromType( final int type ) {

        for( RegionType regType : values() ) {
            if( regType.getType() == type ) {
                return regType;
            }
        }

        return null;

    }


    public static RegionType fromString( final String string ) {

        for( RegionType regType : values() ) {
            if( regType.toString().equalsIgnoreCase( string ) ) {
                return regType;
            }
        }

        return null;

    }

}

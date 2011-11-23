package de.lmu.ifi.dbs.elki.database.ids;

/*
 This file is part of ELKI:
 Environment for Developing KDD-Applications Supported by Index-Structures

 Copyright (C) 2011
 Ludwig-Maximilians-Universität München
 Lehr- und Forschungseinheit für Datenbanksysteme
 ELKI Development Team

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import de.lmu.ifi.dbs.elki.database.ids.integer.TrivialDBIDFactory;
import de.lmu.ifi.dbs.elki.persistent.ByteBufferSerializer;
import de.lmu.ifi.dbs.elki.persistent.FixedSizeByteBufferSerializer;

/**
 * Factory interface for generating DBIDs. See {@link #FACTORY} for the static
 * instance to use.
 * 
 * @author Erich Schubert
 * 
 * @apiviz.stereotype factory
 * @apiviz.uses DBID oneway - - «create»
 * @apiviz.uses DBIDs oneway - - «create»
 * @apiviz.uses DBIDPair oneway - - «create»
 * @apiviz.uses DBIDRange oneway - - «create»
 * @apiviz.uses ArrayModifiableDBIDs oneway - - «create»
 * @apiviz.uses HashSetModifiableDBIDs oneway - - «create»
 * @apiviz.uses TreeSetModifiableDBIDs oneway - - «create»
 * @apiviz.has ByteBufferSerializer oneway - - provides
 */
public interface DBIDFactory {
  /**
   * Static DBID factory to use.
   */
  public static DBIDFactory FACTORY = new TrivialDBIDFactory();
  
  /**
   * Import an integer ID
   * 
   * @param id Integer ID to import
   * @return DBID
   */
  public DBID importInteger(int id);

  /**
   * Generate a single DBID
   * 
   * @return A single DBID
   */
  public DBID generateSingleDBID();

  /**
   * Return a single DBID for reuse.
   * 
   * @param id DBID to deallocate
   */
  public void deallocateSingleDBID(DBID id);

  /**
   * Generate a static DBID range.
   * 
   * @param size Requested size
   * @return DBID range
   */
  public DBIDRange generateStaticDBIDRange(int size);

  /**
   * Deallocate a static DBID range.
   * 
   * @param range Range to deallocate
   */
  public void deallocateDBIDRange(DBIDRange range);

  /**
   * Make a DBID pair from two existing DBIDs.
   * 
   * @param first first DBID
   * @param second second DBID
   * 
   * @return new pair.
   */
  public DBIDPair makePair(DBID first, DBID second);
  
  /**
   * Make a new (modifiable) array of DBIDs.
   * 
   * @return New array
   */
  public ArrayModifiableDBIDs newArray();
  
  /**
   * Make a new (modifiable) hash set of DBIDs.
   * 
   * @return New hash set
   */
  public HashSetModifiableDBIDs newHashSet();
  
  /**
   * Make a new (modifiable) array of DBIDs.
   * 
   * @param size Size hint
   * @return New array
   */
  public ArrayModifiableDBIDs newArray(int size);
  
  /**
   * Make a new (modifiable) hash set of DBIDs.
   * 
   * @param size Size hint
   * @return New hash set
   */
  public HashSetModifiableDBIDs newHashSet(int size);
  
  /**
   * Make a new (modifiable) array of DBIDs.
   * 
   * @param existing existing DBIDs to use
   * @return New array
   */
  public ArrayModifiableDBIDs newArray(DBIDs existing);
  
  /**
   * Make a new (modifiable) hash set of DBIDs.
   * 
   * @param existing existing DBIDs to use
   * @return New hash set
   */
  public HashSetModifiableDBIDs newHashSet(DBIDs existing);
  
  /**
   * Get a serializer for DBIDs
   * 
   * @return DBID serializer 
   */
  public ByteBufferSerializer<DBID> getDBIDSerializer();
  
  /**
   * Get a serializer for DBIDs with static size
   * 
   * @return DBID serializer
   */
  public FixedSizeByteBufferSerializer<DBID> getDBIDSerializerStatic();
  
  /**
   * Get type restriction
   * 
   * @return type restriction for DBIDs
   */
  public Class<? extends DBID> getTypeRestriction();
}
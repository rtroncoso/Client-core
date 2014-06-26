/*******************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package org.ini4j;

import org.ini4j.spi.IniBuilder;
import org.ini4j.spi.IniFormatter;
import org.ini4j.spi.IniHandler;
import org.ini4j.spi.IniParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import java.net.URL;

public class Ini extends BasicProfile implements Persistable, Configurable
{
    private static final long serialVersionUID = -6029486578113700585L;
    private Config _config;
    private File _file;

    public Ini()
    {
        _config = Config.getGlobal();
    }

    public Ini(Reader input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Ini(InputStream input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Ini(URL input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Ini(File input) throws IOException, InvalidFileFormatException
    {
        this();
        _file = input;
        load();
    }

    @Override public Config getConfig()
    {
        return _config;
    }

    @Override public void setConfig(Config value)
    {
        _config = value;
    }

    @Override public File getFile()
    {
        return _file;
    }

    @Override public void setFile(File value)
    {
        _file = value;
    }

    @Override public void load() throws IOException, InvalidFileFormatException
    {
        if (_file == null)
        {
            throw new FileNotFoundException();
        }

        load(_file);
    }

    @Override public void load(InputStream input) throws IOException, InvalidFileFormatException
    {
        load(new InputStreamReader(input, getConfig().getFileEncoding()));
    }

    @Override public void load(Reader input) throws IOException, InvalidFileFormatException
    {
        IniParser.newInstance(getConfig()).parse(input, newBuilder());
    }

    @Override public void load(File input) throws IOException, InvalidFileFormatException
    {
        load(input.toURI().toURL());
    }

    @Override public void load(URL input) throws IOException, InvalidFileFormatException
    {
        IniParser.newInstance(getConfig()).parse(input, newBuilder());
    }

    @Override public void store() throws IOException
    {
        if (_file == null)
        {
            throw new FileNotFoundException();
        }

        store(_file);
    }

    @Override public void store(OutputStream output) throws IOException
    {
        store(new OutputStreamWriter(output, getConfig().getFileEncoding()));
    }

    @Override public void store(Writer output) throws IOException
    {
        store(IniFormatter.newInstance(output, getConfig()));
    }

    @Override public void store(File output) throws IOException
    {
        OutputStream stream = new FileOutputStream(output);

        store(stream);
        stream.close();
    }

    protected IniHandler newBuilder()
    {
        return IniBuilder.newInstance(this);
    }

    @Override protected void store(IniHandler formatter, Profile.Section section)
    {
        if (getConfig().isEmptySection() || (section.size() != 0))
        {
            super.store(formatter, section);
        }
    }

    @Override protected void store(IniHandler formatter, Profile.Section section, String option, int index)
    {
        if (getConfig().isMultiOption() || (index == (section.length(option) - 1)))
        {
            super.store(formatter, section, option, index);
        }
    }

    @Override boolean isTreeMode()
    {
        return getConfig().isTree();
    }

    @Override char getPathSeparator()
    {
        return getConfig().getPathSeparator();
    }

    @Override boolean isPropertyFirstUpper()
    {
        return getConfig().isPropertyFirstUpper();
    }
}

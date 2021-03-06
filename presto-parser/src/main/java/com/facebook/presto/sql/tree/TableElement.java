/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.sql.tree;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

public final class TableElement
        extends Statement
{
    private final String name;
    private final String type;
    private final Optional<String> comment;
    private final boolean partitionKey;

    public TableElement(String name, String type, Optional<String> comment, boolean partitionKey)
    {
        this.name = checkNotNull(name, "name is null");
        this.type = checkNotNull(type, "type is null");
        this.comment = comment;
        this.partitionKey = partitionKey;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public Optional<String> getComment()
    {
        return comment;
    }

    public boolean isPartitionKey()
    {
        return partitionKey;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context)
    {
        return visitor.visitTableElement(this, context);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TableElement o = (TableElement) obj;
        return Objects.equals(this.name, o.name) &&
                Objects.equals(this.type, o.type) &&
                Objects.equals(this.comment, o.comment) &&
                this.partitionKey == o.partitionKey;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, type, comment, partitionKey);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("name", name)
                .add("type", type)
                .add("partitionKey", partitionKey)
                .add("comment", comment)
                .toString();
    }
}

/*
 * Copyright (c) 2014, Monash e-Research Centre
 *  (Monash University, Australia)
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  	* Neither the name of the Monash University nor the names of its
 *  	  contributors may be used to endorse or promote products derived from
 *  	  this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *  EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.eddy.modc.domain.doi;

/**
 * Monash University eResearch Center
 * <p/>
 * Created by simonyu - xiaoming.yu@monash.edu
 * Date: 16/09/2014
 */
public enum DoiRelationType {
    IS_CITED_BY("IsCitedBy"),
    CITES("Cites"),
    IS_SUPPLEMENT_TO("IsSupplementTo"),
    IS_SUPPLEMENTED_BY("IsSupplementedBy"),
    IS_CONTINUED_BY("IsContinuedBy"),
    CONTINUES("Continues"),
    IS_NEW_VERSION_OF("IsNewVersionOf"),
    IS_PREVIOUS_VERSION_OF("IsPreviousVersionOf"),
    IS_PART_OF("IsPartOf"),
    HAS_PART("HasPart"),
    IS_REFERENCED_BY("IsReferencedBy"),
    REFERENCES("References"),
    IS_DOCUMENTED_BY("IsDocumentedBy"),
    DOCUMENTS("Documents"),
    IS_COMPILED_BY("IsCompiledBy"),
    COMPILES("Compiles"),
    IS_VARIANT_FORM_OF("IsVariantFormOf"),
    IS_ORIGINAL_FORM_OF("IsOriginalFormOf");
    private final String value;

    DoiRelationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DoiRelationType fromValue(String v) {
        for (DoiRelationType c : DoiRelationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}

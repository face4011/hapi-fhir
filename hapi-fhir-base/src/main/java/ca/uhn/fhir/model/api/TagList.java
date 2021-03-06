package ca.uhn.fhir.model.api;

/*
 * #%L
 * HAPI FHIR - Core Library
 * %%
 * Copyright (C) 2014 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.ArrayList;
import java.util.List;

public class TagList extends ArrayList<Tag> {

	private static final long serialVersionUID = 1L;
	public static final String ATTR_CATEGORY = "category";
	public static final String ELEMENT_NAME = "TagList";
	public static final String ELEMENT_NAME_LC = ELEMENT_NAME.toLowerCase();

	public Tag addTag(String theScheme, String theTerm, String theLabel) {
		Tag retVal = new Tag(theScheme, theTerm, theLabel);
		add(retVal);
		return retVal;
	}

	public Tag addTag() {
		return addTag(null, null, null);
	}

	public Tag getTag(String theScheme, String theTerm) {
		for (Tag next : this) {
			if (theScheme.equals(next.getScheme()) && theTerm.equals(next.getTerm())) {
				return next;
			}
		}
		return null;
	}

	public List<Tag> getTagsWithScheme(String theScheme) {
		ArrayList<Tag> retVal = new ArrayList<Tag>();
		for (Tag next : this) {
			if (theScheme.equals(next.getScheme())) {
				retVal.add(next);
			}
		}
		return retVal;
	}

}

/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2009 - 2012 Board of Regents of the University of
 * Wisconsin-Madison, Broad Institute of MIT and Harvard, and Max Planck
 * Institute of Molecular Cell Biology and Genetics.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */

package imagej.core.plugins.axispos;

import imagej.ImageJ;
import imagej.event.EventHandler;
import imagej.event.EventService;
import imagej.ext.display.event.input.KyPressedEvent;
import imagej.ext.display.event.input.KyReleasedEvent;
import imagej.service.AbstractService;
import imagej.service.Service;


/**
 * A service that allows plugins to get the current keyboard modifier statuses
 * 
 * @author Barry DeZonia
 *
 */
@Service
public class KeyboardModifiersService extends AbstractService {

	private EventService eventService;
	
	private boolean altDown = false;
	private boolean altGrDown = false;
	private boolean ctrlDown = false;
	private boolean metaDown = false;
	private boolean shiftDown = false;
	
	public KeyboardModifiersService(ImageJ context) {
		// NB : required by SezPoz
		super(context);
		throw new UnsupportedOperationException();
	}

	public KeyboardModifiersService(final ImageJ context,
		final EventService eventService)
	{
		super(context);
		this.eventService = eventService;

		subscribeToEvents(eventService);
	}

	public EventService getEventService() {
		return eventService;
	}

	public boolean isAltDown()    { return altDown; }
	public boolean isAltGrDown()  { return altGrDown; }
	public boolean isCtrlDown()   { return ctrlDown; }
	public boolean isMetaDown()   { return metaDown; }
	public boolean isShiftDown()  { return shiftDown; }
	
	@EventHandler
	void onEvent(KyPressedEvent evt) {
		altDown = evt.getModifiers().isAltDown();
		altGrDown = evt.getModifiers().isAltGrDown();
		ctrlDown = evt.getModifiers().isCtrlDown();
		metaDown = evt.getModifiers().isMetaDown();
		shiftDown = evt.getModifiers().isShiftDown();
	}

	@EventHandler
	void onEvent(KyReleasedEvent evt) {
		altDown = evt.getModifiers().isAltDown();
		altGrDown = evt.getModifiers().isAltGrDown();
		ctrlDown = evt.getModifiers().isCtrlDown();
		metaDown = evt.getModifiers().isMetaDown();
		shiftDown = evt.getModifiers().isShiftDown();
	}
}
package com.snap.linkshortener.app.service.impl;

import com.snap.linkshortener.app.repository.LinkRepository;
import com.snap.linkshortener.app.repository.entity.Links;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {SimpleLink.class})
@ExtendWith(SpringExtension.class)
class SimpleLinkTest {

    @MockBean
    private LinkRepository linkRepository;

    @Autowired
    private SimpleLink simpleLink;

    /**
     * Method under test: {@link SimpleLink#getShortLinkratio(String)}
     */
    @Test
    void testGetShortLinkRatio_NewShortLink() {
        Links links = new Links();
        links.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        links.setExpireDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        links.setId(1L);
        links.setOriginalLink("Original Link");
        links.setRate(1);
        links.setShortKey("Short Key");
        links.setShortLink("Short Link");
        links.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Links> ofResult = Optional.of(links);
        when(linkRepository.findByShortLink(Mockito.<String>any())).thenReturn(ofResult);
        simpleLink.getShortLinkratio("Short Link");
        verify(linkRepository).findByShortLink(Mockito.<String>any());
    }

    /**
     * Method under test: {@link SimpleLink#getShortLinkratio(String)}
     */
    @Test
    void testGetShortLinkRatio2_NewShortLink() {
        Links links = mock(Links.class);
        when(links.getRate()).thenReturn(1);
        when(links.getOriginalLink()).thenReturn("Original Link");
        when(links.getShortLink()).thenReturn("Short Link");
        doNothing().when(links).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(links).setExpireDate(Mockito.<LocalDateTime>any());
        doNothing().when(links).setId(anyLong());
        doNothing().when(links).setOriginalLink(Mockito.<String>any());
        doNothing().when(links).setRate(Mockito.<Integer>any());
        doNothing().when(links).setShortKey(Mockito.<String>any());
        doNothing().when(links).setShortLink(Mockito.<String>any());
        doNothing().when(links).setUpdateTime(Mockito.<LocalDateTime>any());
        links.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        links.setExpireDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        links.setId(1L);
        links.setOriginalLink("Original Link");
        links.setRate(1);
        links.setShortKey("Short Key");
        links.setShortLink("Short Link");
        links.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Links> ofResult = Optional.of(links);
        when(linkRepository.findByShortLink(Mockito.<String>any())).thenReturn(ofResult);
        simpleLink.getShortLinkratio("Short Link");
        verify(linkRepository).findByShortLink(Mockito.<String>any());
        verify(links).getRate();
        verify(links).getOriginalLink();
        verify(links).getShortLink();
        verify(links).setCreateTime(Mockito.<LocalDateTime>any());
        verify(links).setExpireDate(Mockito.<LocalDateTime>any());
        verify(links).setId(anyLong());
        verify(links).setOriginalLink(Mockito.<String>any());
        verify(links).setRate(Mockito.<Integer>any());
        verify(links).setShortKey(Mockito.<String>any());
        verify(links).setShortLink(Mockito.<String>any());
        verify(links).setUpdateTime(Mockito.<LocalDateTime>any());
    }

    /**
     * Method under test: {@link SimpleLink#getShortLinkratio(String)}
     */
    @Test
    void testGetShortLinkRatio3_NewShortLink() {
        when(linkRepository.findByShortLink(Mockito.<String>any())).thenReturn(Optional.empty());
        Links links = mock(Links.class);
        when(links.getRate()).thenReturn(1);
        when(links.getOriginalLink()).thenReturn("Original Link");
        when(links.getShortLink()).thenReturn("Short Link");
        doNothing().when(links).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(links).setExpireDate(Mockito.<LocalDateTime>any());
        doNothing().when(links).setId(anyLong());
        doNothing().when(links).setOriginalLink(Mockito.<String>any());
        doNothing().when(links).setRate(Mockito.<Integer>any());
        doNothing().when(links).setShortKey(Mockito.<String>any());
        doNothing().when(links).setShortLink(Mockito.<String>any());
        doNothing().when(links).setUpdateTime(Mockito.<LocalDateTime>any());
        links.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        links.setExpireDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        links.setId(1L);
        links.setOriginalLink("Original Link");
        links.setRate(1);
        links.setShortKey("Short Key");
        links.setShortLink("Short Link");
        links.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertNull(simpleLink.getShortLinkratio("Short Link"));
        verify(linkRepository).findByShortLink(Mockito.<String>any());
        verify(links).setCreateTime(Mockito.<LocalDateTime>any());
        verify(links).setExpireDate(Mockito.<LocalDateTime>any());
        verify(links).setId(anyLong());
        verify(links).setOriginalLink(Mockito.<String>any());
        verify(links).setRate(Mockito.<Integer>any());
        verify(links).setShortKey(Mockito.<String>any());
        verify(links).setShortLink(Mockito.<String>any());
        verify(links).setUpdateTime(Mockito.<LocalDateTime>any());
    }
}


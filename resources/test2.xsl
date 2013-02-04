<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="no"/>

    <xsl:template match="/|comment()|processing-instruction()">
            <x>

                <xsl:apply-templates select="/html/body/div[@id='container']/div[5]/div[2]/div[1]/div[3]"/>

            </x>
    </xsl:template>

    <xsl:template match="*">
        <xsl:copy>
            <xsl:value-of select="text()"></xsl:value-of>
        </xsl:copy>
    </xsl:template>




</xsl:stylesheet>